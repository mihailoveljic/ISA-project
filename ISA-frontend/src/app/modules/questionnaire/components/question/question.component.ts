import { Question } from './../../../../models/Question';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { animate, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
})
export class QuestionComponent implements OnInit {

  @Input()
  question!: Question;

  @Input()
  questionNumber!: number;

  @Input()
  progress!: number;

  @Output()
  onAnswer = new EventEmitter<boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  yes(){
    this.onAnswer.emit(true);
  }

  no(){
    this.onAnswer.emit(false);
  }

}
