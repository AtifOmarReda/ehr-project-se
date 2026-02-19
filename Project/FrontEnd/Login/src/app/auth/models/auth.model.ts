export interface LoginRequest {
    username: string;
    password: string;
}

export interface JwtAuthenticationResponse {
    accessToken: string;
    refreshToken: string;
}