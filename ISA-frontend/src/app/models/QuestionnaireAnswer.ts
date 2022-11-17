import { Question } from './Question';
export interface QuestionnaireAnswer{
  id: number;
  question: Question;
  answer: boolean;
}