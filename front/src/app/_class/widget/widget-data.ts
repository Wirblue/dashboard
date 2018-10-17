export class WidgetData {
  title: string;
  subtitle: string;
  icon_image: string;
  background_image: string;

  constructor(title: string, subtitle: string, icon_image: string, background_image: string) {
    this.title = title;
    this.subtitle = subtitle;
    this.icon_image = icon_image;
    this.background_image = background_image;
  }
}

export class WidgetDataError extends WidgetData {
  constructor(subtitle: string) {
    super('ERROR', subtitle, null, null);
  }
}
