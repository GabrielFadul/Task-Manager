import { Component } from '@angular/core';
import { LoginForm } from './login-form/login-form';

@Component({
  selector: 'app-login-page-component',
  standalone: true,
  imports: [LoginForm],
  templateUrl: './login-page-component.html',
  styleUrl: './login-page-component.scss',
})
export class LoginPageComponent {

}
