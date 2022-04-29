// infix.java
import java.io.*;
import MyStackQueue.*;
 
class infix {
	static Queue infixToPostfix(String s) {
   Stack theStack = new Stack();
   Tokenizer T = new Tokenizer(s);
 
   Queue Myqueue = new Queue();
   while (T.moreTokens()) {
   
      Token Tkn = T.nextToken();
      if (Tkn instanceof Operand){
            Myqueue.enqueue(Tkn);
            }
      else if (Opr.operator== '(') {
            theStack.push(Opr);
            }
            
      else if (Opr.operator==')') {
           while(((Operator)theStack.top()).operator != "(" && !thestack.isEmpty()){ 
                 Myqueue.enqueue(theStack.pop());
                 }
      }
          if(!thestack.isEmpty() && thestack.top().equals('(')){
                 thestack.pop();
                 }
            
     
            
       while (((Operator)theStack.top()).operator <= precedence() )
          Myqueue.enqueue(theStack.pop());
             theStack.pop();
             theStack.push(Opr);
     }  
	}

	static int evaluePostfix(Queue Post) {
       Stack theStack = new Stack();

    while (T.moreTokens()) {
    if (Tkn instanceof Operand)
       theStack.push(Opr);
     
       if (((Operator)theStack.top()).operator!='(')
         theStack.pop();
         switch(Opr.operator) {
         case '+': result = opnd1 + opnd2; break;
                               }
     }
	}

	public static void main(String[] args) throws IOException {
 	Queue Post;
	while(true) {
		System.out.print("Enter infix: ");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		if ( s.equals("") ) break;
		Post = infixToPostfix(s);
		System.out.println("Postfix is " + Post.toString() + '\n');
		int result = evaluePostfix(Post);
		System.out.println("Result is " + result + '\n');
	}
  }
}
