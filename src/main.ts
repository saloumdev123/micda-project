
import { bootstrapApplication, BrowserModule } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { HttpClient } from '@angular/common/http';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import {
  TranslateLoader,
  TranslateModule,
  TranslateModuleConfig,
} from '@ngx-translate/core';
import { importProvidersFrom } from '@angular/core';

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json'); // Changed to correct path
}

bootstrapApplication(AppComponent, appConfig).catch((err) =>
  console.error(err)
);

const translateModuleConfig: TranslateModuleConfig = {
  defaultLanguage: 'fr', // Set default language to French
  loader: {
    provide: TranslateLoader,
    useFactory: HttpLoaderFactory,
    deps: [HttpClient],
  },
};

importProvidersFrom(
  BrowserModule,
  TranslateModule.forRoot(translateModuleConfig)
);