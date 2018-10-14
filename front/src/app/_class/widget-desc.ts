export class WidgetDesc {
  name: string;
  description: string;
  params: WidgetDestParams[];

  constructor(name: string, description: string, params: WidgetDestParams[]) {
    this.name = name;
    this.description = description;
    this.params = params;
  }
}


export class WidgetDestParams {
  name: string;
  value: string;
  type: string;

  constructor(name: string, type: string, value: string) {
    this.name = name;
    this.value = value;
    this.type = type;
  }
}
