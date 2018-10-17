export class WidgetParam {
  name: string;
  value: string;
  type: string;

  constructor(name: string, type: string, value: string) {
    this.name = name;
    this.value = value;
    this.type = type;
  }
}
