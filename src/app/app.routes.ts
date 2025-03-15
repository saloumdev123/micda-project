import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { ResourceComponent } from './backoffice/components/resource/resource.component';
import { CarrersJobsComponent } from './carrers-jobs/carrers-jobs.component';
import { ServicesComponent } from './services/services.component';
import { HeroComponent } from './hero/hero.component';
import { JobApplicationComponent } from './backoffice/components/job-application/job-application.component';
import { JobComponent } from './backoffice/components/job/job.component';
import { SubscriptionComponent } from './backoffice/components/subscription/subscription.component';
import { HeroAboutComponent } from './hero-about/hero-about.component';
import { ArticleRoomComponent } from './article-room/article-room.component';
import { CaseStudyOneComponent } from './case-study-one/case-study-one.component';
import { CaseStudyTwoComponent } from './case-study-two/case-study-two.component';
import { ProductsComponent } from './products/products.component';

export const routes: Routes = [
  // { path: '', pathMatch: 'full', component: HomeComponent },
  { path: '', component: HomeComponent },

  { path: 'about-us', component: HeroAboutComponent },
  { path: 'services', component: ServicesComponent },
  { path: 'products', component: ProductsComponent },

  { path: 'resources', component: ArticleRoomComponent },

  { path: 'corporate-careers-overview', component: CarrersJobsComponent },
  { path: 'job-applications', component: JobApplicationComponent },
  { path: 'job-list', component: JobComponent },
  { path: 'top-article', component: CaseStudyOneComponent },
  { path: 'baye-article', component: CaseStudyTwoComponent },

  { path: 'subscriptions-add', component: SubscriptionComponent },
];
