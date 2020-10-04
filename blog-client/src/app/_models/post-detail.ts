import { CommentDetail } from './comment-detail';

export class PostDetail {
  id: number;
  title: string;
  category: string;
  author: string;
  content: string;
  createdAt: Date;
  comments: Array<CommentDetail>;
}
