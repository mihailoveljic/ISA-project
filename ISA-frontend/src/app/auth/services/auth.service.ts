import { Injectable } from '@angular/core';
import { OAuthService, AuthConfig,  OAuthErrorEvent, OAuthSuccessEvent, NullValidationHandler } from 'angular-oauth2-oidc';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import jwt_decode from 'jwt-decode';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  /**
   * The OpenID-Connect configuration using the Authorization Code flow
   */
  authConfig: AuthConfig = {
      issuer: 'http://localhost:28080/auth/realms/blood-bank',
      redirectUri: window.location.origin + '/callback',
      postLogoutRedirectUri: window.location.origin + '',
      clientId: 'blood-bank-angular',
      scope: 'openid profile email offline_access',
      responseType: 'code',
      sessionChecksEnabled: true
  };

  constructor(private oauthService: OAuthService,
              private router: Router) {
      this.configure();
      // For SSO logout
      this.oauthService.events.pipe(filter(e => e.type === 'session_changed')).subscribe(e => {
          this.logout();
      });
  }

  /**
   * Extract the roles from the realm_access claim within the Keycloak generated access token (JWT)
   */
  public getUser(): User | undefined {
    if(!this.authenticated()) return undefined;
    const accessToken: any = jwt_decode(this.oauthService.getAccessToken());
    return accessToken.user;
  }


  /**
   * Will kick-off the OpenID Connect Authorization Code flow (Based on the configured authConfig#issuer)
   */
  public login(): void {
      this.oauthService.initLoginFlow();
  }

  /**
   * Will execute a logout operation by re-directing to Keycloaks logout endpoint and successively to
   * to a configured logout path (Configured above in authConfig#postLogoutRedirectUri)
   */
  public logout(): void {
      this.oauthService.logOut();
  }

  /**
   * Will hook into the OAuth 'token_recieved' event and perform a re-direct to the home page
   */
  public redirectOnCallback(): void {
      this.oauthService.events.subscribe(event => {
          if (event instanceof OAuthErrorEvent) {
              console.error(event);
          } else if (event instanceof OAuthSuccessEvent) {
              if (event.type === 'token_received') {
                  this.router.navigateByUrl('');
              }
          }
      });
  }

  /**
  * Determines if the current user has a valid id and access tokens
  * Returns true if both IdToken and AccessToken exist within the session storage, false otherwise
  */
  public authenticated(): boolean {
      return this.oauthService.hasValidIdToken() && this.oauthService.hasValidAccessToken();
  }

  /**
   * Configures the Angular OpenID Connect client
   */
  private configure(): void {
      this.oauthService.configure(this.authConfig);
      this.oauthService.setupAutomaticSilentRefresh();
      this.oauthService.tokenValidationHandler = new NullValidationHandler();
      this.oauthService.loadDiscoveryDocumentAndTryLogin();
  }


} 