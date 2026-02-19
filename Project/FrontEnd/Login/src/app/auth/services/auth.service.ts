import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LoginRequest, JwtAuthenticationResponse } from '../models/auth.model';
import { TokenService } from './token.service';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private api = environment.apiUrl;

    constructor(
        private http: HttpClient,
        private tokenService: TokenService
    ) { }

    login(data: LoginRequest): Observable<JwtAuthenticationResponse> {
        return this.http.post<JwtAuthenticationResponse>(`${this.api}/login`, data)
            .pipe(
                tap(res => {
                    this.tokenService.setAccessToken(res.accessToken);
                    this.tokenService.setRefreshToken(res.refreshToken);
                })
            );
    }

    refreshToken(): Observable<JwtAuthenticationResponse> {
        return this.http.post<JwtAuthenticationResponse>(`${this.api}/refresh`, {
            refreshToken: this.tokenService.getRefreshToken()
        }).pipe(
            tap(res => {
                this.tokenService.setAccessToken(res.accessToken);
                this.tokenService.setRefreshToken(res.refreshToken);
            })
        );
    }

    // auth.service.ts
    logout(): Observable<any> {
        // On appelle l'API de logout
        // L'intercepteur ajoutera automatiquement le token si l'utilisateur est encore connecté
        return this.http.post(`${this.api}/logout`, {}).pipe(
            tap(() => {
                // Une fois que l'appel API est initié/réussi, on vide le localStorage
                this.tokenService.clear();
            }),
            catchError((err) => {
                // Même en cas d'erreur serveur (ex: token déjà expiré), 
                // on vide le local pour ne pas bloquer l'utilisateur
                this.tokenService.clear();
                return throwError(() => err);
            })
        );
    }

    isAuthenticated(): boolean {
        return !!this.tokenService.getAccessToken();
    }

}