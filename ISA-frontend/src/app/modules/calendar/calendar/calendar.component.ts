import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
} from '@angular/core';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
  addMinutes,
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { EventColor } from 'calendar-utils';
import {CalendarService} from '../calendar.service'
import {AppointmentDTO} from './appointmentDTO'
import { Router } from '@angular/router';
const colors: Record<string, EventColor> = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
  green: {
    primary: '#00ff00',
    secondary: '#aaffaa',
  },
};

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class CalendarComponent implements OnInit{
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any> | undefined;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  res : AppointmentDTO[] = [];

  modalData: {
    action: string;
    event: CalendarEvent;
  } | undefined;

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh = new Subject<void>();

  events: CalendarEvent[] = [];

  activeDayIsOpen: boolean = true;

  constructor(private modal: NgbModal, private calendarService: CalendarService, private router: Router) {
  }
  
  ngOnInit(): void {
    
    this.calendarService.getAllAppointments().subscribe(res => {
      this.res = res
      for(let i=0; i<this.res.length; i++){  
      this.addNewAppointmentToCalendar(this.res[i])
   }

  })
    
}

  addNewAppointmentToCalendar(appointment: AppointmentDTO): void{
    let title: string = "Free appointment: " + appointment.description;
    let color = { ...colors['blue'] };
    if (appointment.appointmentStatus == 'CANCELED'){
      color = { ...colors['red'] }
    }
    if (appointment.appointmentStatus == 'FREE'){
      color = { ...colors['yellow'] } 
    }
    if (appointment.appointmentStatus == 'SCHEDULED'){
      title = appointment.user + ": " + appointment.description;
      color = { ...colors['blue'] } 
    }
    if (appointment.appointmentStatus == 'DONE'){
      title = appointment.user + ": " + appointment.description;
      color = { ...colors['green'] } 
    }

    let date: Date = new Date(appointment.date)
    this.events.push({
      start: date,
      end: addMinutes(date, appointment.duration),
      title: title,
      color: color,
      resizable: {
        beforeStart: false,
        afterEnd: true,
      },
      draggable: true,
  })
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }
  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
    if(event.start.getDate() === new Date().getDate() && event.color?.primary == colors['blue'].primary){
      var id = 0;
      var email = '';
      for(let i=0; i<this.res.length; i++){  
        if(event.title.split(":")[1] === " " + this.res[i].description)
        id = this.res[i].id;
        email = this.res[i].email;
      }
      this.router.navigate(['/appointment'], {queryParams : {'appointment-id': id, 'email': email}})
    }
  }

  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors['red'],
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }


}
