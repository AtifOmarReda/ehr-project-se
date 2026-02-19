import { Injectable } from '@angular/core';
import {
    HttpInterceptor,
    HttpRequest,
    HttpHandler,
    HttpErrorResponse
} from '@angular/common/http';
import { catchError, switchMap, throwError } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { TokenService } from '../services/token.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    constructor(
        private tokenService: TokenService,
        private authService: AuthService
    ) { }

    intercept(req: HttpRequest<any>, next: HttpHandler) {

        const token = this.tokenService.getAccessToken();

        let authReq = req;
        if (token) {
            authReq = req.clone({
                setHeaders: { Authorization: `Bearer ${token}` }
            });
        }

        return next.handle(authReq).pipe(
            catchError((error: HttpErrorResponse) => {
                if (error.status === 401) {
                    return this.authService.refreshToken().pipe(
                        switchMap(res => {
                            const newReq = req.clone({
                                setHeaders: {
                                    Authorization: `Bearer ${res.accessToken}`
                                }
                            });
                            return next.handle(newReq);
                        })
                    );
                }
                return throwError(() => error);
            })
        );
    }
}