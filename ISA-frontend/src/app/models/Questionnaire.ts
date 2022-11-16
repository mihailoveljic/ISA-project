import { User } from 'src/app/auth/models/user';
import { QuestionnaireAnswer } from "./QuestionnaireAnswer";

export interface Questionnaire{
  id: number;
  title: string;
  userEmail?: string;
  questionnaireAnswers: Array<QuestionnaireAnswer>;
}