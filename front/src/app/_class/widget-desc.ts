export class WidgetDesc {
  name: string;
  desc: string;
  params: WidgetDestParams[];

  constructor(name: string, desc: string, params: WidgetDestParams[]) {
    this.name = name;
    this.desc = desc;
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
