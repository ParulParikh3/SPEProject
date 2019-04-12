import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {DashBoardComponent} from '../app/dash-board/dash-board.component';
import {MyeventsComponent} from '../app/myevents/myevents.component';
import {RegisterFormComponent} from '../app/register-form/register-form.component';
import {EventListComponent} from '../app/event-list/event-list.component';
import { ParticipantComponent } from './participant/participant.component';
import {OrganizerComponent} from './organizer/organizer.component';
import {EventCreationFormComponent} from './event-creation-form/event-creation-form.component';
import {UndercreationEventDetailsComponent} from './undercreation-event-details/undercreation-event-details.component';
import {SpeakersSponsorsListComponent} from './speakers-sponsors-list/speakers-sponsors-list.component';
import {AuthGuard} from './auth.guard';

const parentModuleRoutes: Routes = [
    {
        path:'dashboard',
        component: DashBoardComponent,
        children: [                          //<---- child components declared here
            {
                path:'eventlist',
                component: EventListComponent
            },
            {
                path:'add',
                component: RegisterFormComponent
            },
            {
                path:'participant/:eventname',
                component: ParticipantComponent
            },
            {
                path: 'myevent',
                component:MyeventsComponent
            },
            {
                path:'organizers',
                component:OrganizerComponent
            },
            {
                path:'newevent',
                component:EventCreationFormComponent
            },

            {
                path:'undercreation/:eventname',
                component:UndercreationEventDetailsComponent
            },
            {
                path:'request/:eventid',
                component:SpeakersSponsorsListComponent
            },
        ],
        canActivate: [AuthGuard]
    }
    ];
    
    @NgModule({
        imports: [
            RouterModule.forChild(parentModuleRoutes)
        ],
        declarations: [],
        exports: [
            RouterModule
        ]
    })
    export class ParentRoutingModule { }