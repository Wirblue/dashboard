export class Alert {
  title: string;
  desc: string;
  css_class: string;

  constructor(title: string, desc: string, css_class: string) {
    this.title = title;
    this.desc = desc;
    this.css_class = css_class;
  }
}
