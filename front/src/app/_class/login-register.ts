export class LoginRegister {
  username: string;
  password: string;
  password_confirmation: string;

  constructor(username: string, password: string, password_confirmation: string) {
    this.username = username;
    this.password = password;
    this.password_confirmation = password_confirmation;
  }
}
