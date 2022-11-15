import { QuestionService } from './../../services/question.service';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Question } from '../../../../models/Question';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css'],
})
export class QuestionsComponent implements OnInit {

  questions: Array<Question> = new Array<Question>();
  nextQuestion?: Question;
  nextQuestionNumber: number = 0;

  allQuestionsAnswered: boolean = false;

  userAnswers = new Array();

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.getAllQuestions();
  }

  getAllQuestions(){
    this.questionService.getAll().subscribe((response: any) =>{
      this.questions = response.questions;
      this.questions = this.questions.reverse();
      this.nextQuestion = this.questions.pop();
      this.nextQuestionNumber = 1;
    });
  }

  saveAnswer(answer: boolean){
    this.userAnswers.push(answer)
    if(this.questions.length){
      this.nextQuestion = this.questions.pop();
      this.nextQuestionNumber++;
    }else{
      this.allQuestionsAnswered = true;
    }
    
  }

}
