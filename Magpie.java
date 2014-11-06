/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie //changed so it's not 2
{
  /**
   * Get a default greeting  
   * @return a greeting
   */
  public String getGreeting()
  {
    return "Hello, let's talk.";
  }
  
  /**
   * Gives a response to a user statement
   * 
   * @param statement
   *            the user statement
   * @return a response based on the rules given
   */
  public String getResponse(String statement) //methods prioritized by where they're written in the code
  {                                           //ie "mother no" asks why so negative
    String response = "";                     //issue with "no mother" because no " " before "no"
    if (statement.indexOf("no") >= 0)     //added a space to the front of "no" so words like
    {                                       // "know" and "connor" won't be taken as negative
      response = "Why so negative?";
    }
    else if (findKeyword(statement, "mother") >= 0
               || findKeyword(statement, "father") >= 0
               || findKeyword(statement, "sister") >= 0
               || findKeyword(statement, "brother") >= 0)
    {
      response = "Tell me more about your family.";
    }
    else if (statement.indexOf("cat") >= 0       //if domesticated animals mentioned
               || findKeyword(statement, "dog") >= 0   //asks to tell more about pets
               || findKeyword(statement, "bird") >= 0
               || findKeyword(statement, "iguana") >= 0)
    {
      response = "Tell me more about your pets.";
    }
    else if (findKeyword(statement, "Kiang") >= 0       //responds favorably to teachers
               || findKeyword(statement, "Landgraf") >= 0)
    {
      response = "Dude, that teacher's so cool!";
    }
    else if (statement.trim().length() == 0)  //reads statement, trims spaces on sides, checks length
    {
      response = "Please say something.";
    }
    else if (findKeyword(statement, "am") >= 0)
    {
      response = "Are you?";
    }
    else if (((findKeyword(statement, "wet") >= 0) && (findKeyword(statement, "today") >= 0)) //if the statmement contains
               || ((findKeyword(statement, "hot") >= 0) && (findKeyword(statement, "today") >= 0))     //both of these things
               || ((findKeyword(statement, "cold") >= 0) && (findKeyword(statement, "today") >= 0)))   //or both of these
    {
      response = "Tell me more about today's weather.";
    }
    else if (findKeyword(statement, "thank you") >= 0
               || findKeyword(statement, "Thank you") >= 0)
    {
      response = "You're welcome.";
    }
    else if (findKeyword(statement, "homework") >= 0
               || findKeyword(statement, "school") >= 0)
    {
      int multiples = 2;                          //gives a different answer based on random
      double f = Math.random();
      int randResponse = (int)(f * multiples);
      String otherResponse = "";
      if (randResponse == 0)
      {
        otherResponse = "Do you like school?";
      }
      if (randResponse == 1)
      {
        otherResponse = "Have you done your homework?";
      }
      response = otherResponse;
    }
    else
    {
      response = getRandomResponse();
    }
    return response;
  }
  
  private int findKeyword(String statement, String goal,
                          int startPos)
  {
    String phrase = statement.trim();
    // The only change to incorporate the startPos is in
    // the line below
    int psn = phrase.toLowerCase().indexOf(
                                           goal.toLowerCase(), startPos);
    
    // Refinement--make sure the goal isn't part of a
    // word
    while (psn >= 0)
    {
      // Find the string of length 1 before and after
      // the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring(psn - 1, psn)
          .toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(
                                 psn + goal.length(),
                                 psn + goal.length() + 1)
          .toLowerCase();
      }
      
      // If before and after aren't letters, we've
      // found the word
      if (((before.compareTo("a") < 0) || (before
                                             .compareTo("z") > 0)) // before is not a
            // letter
            && ((after.compareTo("a") < 0) || (after
                                                 .compareTo("z") > 0)))
      {
        return psn;
      }
      
      // The last position didn't work, so let's find
      // the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(),
                           psn + 1);
      
    }
    
    return -1;
  }
  
  /**
   * Search for one word in phrase. The search is not case
   * sensitive. This method will check that the given goal
   * is not a substring of a longer string (so, for
   * example, "I know" does not contain "no"). The search
   * begins at the beginning of the string.
   * 
   * @param statement
   *            the string to search
   * @param goal
   *            the string to search for
   * @return the index of the first occurrence of goal in
   *         statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal)
  {
    return findKeyword(statement, goal, 0);
  }
  
  /**
   * Pick a default response to use if nothing else fits.
   * @return a non-committal string
   */
  private String getRandomResponse()
  {
    final int NUMBER_OF_RESPONSES = 6;
    double r = Math.random();
    int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
    String response = "";
    
    if (whichResponse == 0)
    {
      response = "Interesting, tell me more.";
    }
    else if (whichResponse == 1)
    {
      response = "Hmmm.";
    }
    else if (whichResponse == 2)
    {
      response = "Do you really think so?";
    }
    else if (whichResponse == 3)
    {
      response = "You don't say.";
    }
    else if (whichResponse == 4)
    {
      response = "Why do you say that?";
    }
    else if (whichResponse == 5)
    {
      response = "I'm intrigued.";
    }
    
    return response;
  }
}
