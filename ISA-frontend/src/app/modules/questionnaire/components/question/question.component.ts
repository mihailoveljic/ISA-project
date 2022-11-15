import { Question } from './../../../../models/Question';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class QuestionComponent implements OnInit {

  @Input()
  question!: Question;

  @Input()
  questionNumber!: number;

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
