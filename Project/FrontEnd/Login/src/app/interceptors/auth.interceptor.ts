import {
    HttpInterceptorFn,
    HttpErrorResponse,
    HttpRequest,
    HttpHandlerFn,
    HttpEvent
} from '@angular/common/http';
import { inject } from '@angular/core';
import { TokenService } from '../auth/services/token.service';
import { AuthService } from '../auth/services/auth.service';
import { Router } from '@angular/router';
import { catchError, switchMap, throwError, BehaviorSubject, filter, take, Observable } from 'rxjs';

// Variable pour éviter de lancer plusieurs refresh en même temps
let isRefreshing = false;
const refreshTokenSubject: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null);

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> => {
    const tokenService = inject(TokenService);
    const authService = inject(AuthService);
    const router = inject(Router);

    const token = tokenService.getAccessToken();
    let authReq = req;

    if (token) {
        authReq = req.clone({ setHeaders: { Authorization: `Bearer ${token}` } });
    }

    return next(authReq).pipe(
        catchError((error: HttpErrorResponse) => {
            // Si l'erreur est 401 et qu'on n'est pas déjà en train de rafraîchir
            if (error.status === 401 && !req.url.includes('/login') && !req.url.includes('/refresh')) {
                return handle401Error(req, next, authService, tokenService, router);
            }
            return throwError(() => error);
        })
    );
};

/**
 * Fonction interne typée pour gérer le cycle de rafraîchissement
 */
function handle401Error(
    req: HttpRequest<unknown>,
    next: HttpHandlerFn,
    authService: AuthService,
    tokenService: TokenService,
    router: Router
): Observable<HttpEvent<unknown>> { // <-- Le type de retour doit être explicite ici
    if (!isRefreshing) {
        isRefreshing = true;
        refreshTokenSubject.next(null);

        return authService.refreshToken().pipe(
            switchMap((res) => {
                isRefreshing = false;
                refreshTokenSubject.next(res.accessToken);

                return next(req.clone({
                    setHeaders: { Authorization: `Bearer ${res.accessToken}` }
                }));
            }),
            catchError((err) => {
                isRefreshing = false;
                tokenService.clear();
                router.navigate(['/login']);
                return throwError(() => err);
            })
        );
    } else {
        return refreshTokenSubject.pipe(
            filter(token => token !== null),
            take(1),
            switchMap((token) => next(req.clone({
                setHeaders: { Authorization: `Bearer ${token}` }
            })))
        );
    }
}