// LongInt ADT for unbounded integers

public class LongInt {
  public int[] numArray;
  String numString;
  boolean isPositive;
  // constructor
  public LongInt(String s) {
    numString = s;
    // 음수인 경우
    if (s.charAt(0) =='-') {
      isPositive = false;
      numArray = new int[s.length()-1];
      for (int i = 1; i < s.length(); i++) {
        numArray[i-1] = -Integer.parseInt(s.substring(i,i+1)) ;
      }
    } else {
      // 양수인 경우
      isPositive = true;
      numArray = new int[(int) s.length()];
      for (int i = 0; i <s.length(); i++) {
        numArray[i] = Integer.parseInt(s.substring(i,i+1)) ;
      }
    }

  }

  private LongInt changeDirection() {
    int[] resultArray = new int[numArray.length];
    for (int i = 0; i <resultArray.length ; i++) {
      resultArray[i] = - numArray[i];
    }
    // 원래 음수였다면
    if (numString.charAt(0) == '-') {
      return new LongInt(parseResultArray(resultArray,true));
    // 원래 양수였다면
    } else {
      return new LongInt(parseResultArray(resultArray,false));
    }
  }
  private String parseResultArray (int[] numArray, boolean isPositive) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < numArray.length; i++) {

      if (numArray[i] == 0 && !result.isEmpty()) {
        result.append(numArray[i]);
      } else if(numArray[i]<0) {
        result.append(-numArray[i]);
      } else if(numArray[i]>0){
        result.append(numArray[i]);
      }

    }
    if (isPositive) {
      return result.toString();
    } else {
      return "-"+result;
    }

  }

  private int findHead(int[] numArray) {
    int i = 0;
    try {
      while (true) {
        if (numArray[i] == 0) {
          i+=1;
        } else {
          return numArray[i];
        }
      }
    } catch (IndexOutOfBoundsException error) {
      return 0;
    }
  }

  // returns 'this' + 'opnd'; Both inputs remain intact.
  public LongInt add(LongInt opnd) {
    // 선언
    int[] resultArray = new  int[Math.max(numArray.length, opnd.numArray.length)];
    // 할당
    for (int i = 0; i < numArray.length; i++) {
      resultArray[resultArray.length-1-i] = numArray[numArray.length-1-i];
    }
    // 연산 진행
    for (int i = 0; i < resultArray.length; i++) {
      try {
        resultArray[resultArray.length-1-i] += opnd.numArray[opnd.numArray.length-1-i];
      } catch (ArrayIndexOutOfBoundsException error) {

      }

    }

    // 수의 부호 확인
    int head = findHead(resultArray);
    // 양수일 때 - 값 정리
    if (head>0) {
      for (int i = 1; i<resultArray.length; i++) {
        if (resultArray[resultArray.length- i]<0) {
          resultArray[resultArray.length- i] +=10;
          resultArray[resultArray.length- i-1] -=1;
        }
        if (resultArray[resultArray.length- i]>=10) {
          resultArray[resultArray.length- i] -=10;
          resultArray[resultArray.length- i-1] +=1;
        }
      }

      return new LongInt(parseResultArray(resultArray,true));
      // 음수일 때 - 값 처리
    } else if (head <0){
      for (int i = 1; i<resultArray.length; i++) {
        if (resultArray[resultArray.length- i]>0) {
          resultArray[resultArray.length- i] -=10;
          resultArray[resultArray.length- i-1] +=1;
        }
        if (resultArray[resultArray.length- i] <= -10) {
          resultArray[resultArray.length- i] += 10;
          resultArray[resultArray.length- i-1] -=1;
        }
      }

      return new LongInt(parseResultArray(resultArray,false));
      // 0일 때
    } else {
      return new LongInt("0");
    }

  }

  // returns 'this' - 'opnd'; Both inputs remain intact.
  public LongInt subtract(LongInt opnd) {
    return add(opnd.changeDirection());
  }

  // returns 'this' * 'opnd'; Both inputs remain intact.
  public LongInt multiply(LongInt opnd) {
    // 빈 리스트 선언 및 할당 - 길이는 최대 길이로
    int[] resultArray = new int[numArray.length + opnd.numArray.length];

    // 연산
    for (int i = 0; i < opnd.numArray.length; i++) {
      for (int j = 0; j < numArray.length; j++) {
        resultArray[resultArray.length-1-i-j] += numArray[numArray.length-1-j] * opnd.numArray[opnd.numArray.length-1-i];
      }
    }

    // 값정리
    for (int i = 0; i < resultArray.length-1; i++) {
      if (resultArray[resultArray.length-1-i] >=10) {
        // 몫을 구하고
        int olim = resultArray[resultArray.length-1-i]/10;
        resultArray[resultArray.length-1-i] -= olim * 10;
        resultArray[resultArray.length-2-i] += olim;
      }
      if (resultArray[resultArray.length-1-i]<=-10) {
        int olim = resultArray[resultArray.length-1-i]/10;
        resultArray[resultArray.length-1-i] -= olim * 10;
        resultArray[resultArray.length-2-i] += olim;
      }
    }
    int head = findHead(resultArray);
    if (head>0) {

      return new LongInt(parseResultArray(resultArray,true));
    } else if (head <0) {

      return new LongInt(parseResultArray(resultArray,false));
    } else {
      return new LongInt("0");
    }


  }

//   print the value of 'this' element to the standard output.
  public void print() {
    System.out.println(numString);
  }

}

