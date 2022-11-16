import { QuestionnaireService } from './../../services/questionnaire.service';
import { QuestionnaireAnswer } from './../../../../models/QuestionnaireAnswer';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Questionnaire } from './../../../../models/Questionnaire';
import { QuestionService } from './../../services/question.service';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Question } from '../../../../models/Question';
import { User } from 'src/app/auth/models/user';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css'],
})
export class QuestionsComponent implements OnInit {

  questions: Array<Question> = new Array<Question>();
  nextQuestion?: Question;
  nextQuestionNumber: number = 0;

  user?: User;

  allQuestionsAnswered: boolean = false;

  userAnswers = new Array();
  questionnaire: Questionnaire;

  constructor(
    private questionService: QuestionService,
    private authService: AuthService,
    private questionnaireService: QuestionnaireService) { 

      this.user = this.authService.getUser();

      this.questionnaire = {
      id: 0,
      title: 'Blood donation questionnaire',
      userEmail: this.user?.email,
      questionnaireAnswers: new Array<QuestionnaireAnswer>()
    }

  }

  ngOnInit(): void {
    this.checkCompleted();
    this.getAllQuestions();
  }

  getAllQuestions(){
    this.questionService.getAll().subscribe((response: any) =>{
      this.questions = response.questions;
      this.questions = this.questions.filter(question => { 
        if(!question.forGender) return true;
        if(question.forGender == this.user?.gender) return true;
        return false;
      } )
      this.questions = this.questions.reverse();
      this.nextQuestion = this.questions.pop();
      this.nextQuestionNumber = 1;
    });
  }

  saveAnswer(answer: boolean){

    if(!this.nextQuestion) return;

    let questionnaireAnswer: QuestionnaireAnswer = {
      id: 0,
      question: this.nextQuestion,
      answer: answer,
    };
    
    this.questionnaire.questionnaireAnswers.push(questionnaireAnswer);


    if(this.questions.length){
      this.nextQuestion = this.questions.pop();
      this.nextQuestionNumber++;
    }else{
      this.allQuestionsAnswered = true;
      this.saveQuestionnaire();
    }
    
  }

  saveQuestionnaire(){
    this.questionnaireService.create(this.questionnaire).subscribe(() =>{
      console.log(this.questionnaire);
    });
  }

  checkCompleted(){
    if(this.user){
      this.questionnaireService.checkCompleted(this.user?.email).subscribe((response: any)=>{
          this.allQuestionsAnswered = response.completed;
      });    
    }
  }

}
