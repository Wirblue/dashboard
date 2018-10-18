export class AuthServiceDesc {

  name: string;
  registered: boolean;

  constructor(name: string, subscribed: boolean) {
    this.name = name;
    this.registered = subscribed;
  }
}
