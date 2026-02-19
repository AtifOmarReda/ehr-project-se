import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { authInterceptor } from './interceptors/auth.interceptor'; // On importe la fonction, pas la classe

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(
      // On oublie withInterceptorsFromDi(), on passe au pur fonctionnel
      withInterceptors([authInterceptor]) 
    )
  ]
};