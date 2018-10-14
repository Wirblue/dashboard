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
  type: string;

  constructor(name: string, type: string) {
    this.name = name;
    this.type = type;
  }
}
