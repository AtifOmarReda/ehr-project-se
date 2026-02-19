import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { TokenService } from '../../services/token.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule], // Important pour les pipes ou directives si besoin
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class DashboardComponent implements OnInit {
  accessToken: string | null = '';
  refreshToken: string | null = '';

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Récupération des tokens au chargement
    this.accessToken = this.tokenService.getAccessToken();
    this.refreshToken = this.tokenService.getRefreshToken();
  }

  onLogout(): void {
    this.authService.logout().subscribe({
      next: (message) => {
        console.log('Réponse serveur:', message); // Affichera "Logged out"
        this.router.navigate(['/login']);
      },
      error: (err) => {
        // Si c'est vraiment une erreur réseau (status != 200)
        console.error('Erreur lors du logout', err);
        // On redirige quand même car le service a déjà fait le clear()
        this.router.navigate(['/login']);
      }
    });
  }


  copyToClipboard(text: string | null): void {
    if (text) {
      navigator.clipboard.writeText(text);
      alert('Token copié !');
    }
  }

}