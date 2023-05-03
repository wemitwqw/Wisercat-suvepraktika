import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../_service/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authHeaders = this.authenticationService.getAuthHeaders();
    if (authHeaders) {
      const authReq = req.clone({ headers: req.headers.set('Authorization', authHeaders.get('Authorization') || '') });
      return next.handle(authReq);
    }
    return next.handle(req);
  }
}