package com.bm470.util;

public class TcCheck {
    private Long tcNo;
    private int [] tcNoArray;



    private void tcToArray () {
        String temp = tcNo.toString();
        tcNoArray = new int[temp.length()];
        for (int i=0;i<tcNoArray.length;i++) {
            tcNoArray[i] = Integer.parseInt(temp.substring(i,(i+1)));
        }
        temp = null;
    }
    public Boolean tcCheck () {
        tcToArray();
        if(tcNoArray != null && tcNoArray.length == 11) {
            boolean exist1 = (tcNoArray[0]+tcNoArray[1]+tcNoArray[2]+tcNoArray[3]+tcNoArray[4]+tcNoArray[5]+tcNoArray[6]+tcNoArray[7]+tcNoArray[8]+tcNoArray[9])%10 == tcNoArray[10];
            boolean exist2 = (((tcNoArray[0]+tcNoArray[2]+tcNoArray[4]+tcNoArray[6]+tcNoArray[8]) * 7 ) + ((tcNoArray[1]+tcNoArray[3]+tcNoArray[5]+tcNoArray[7]) * 9 ))%10 == tcNoArray[9];
            boolean exist3 = ((tcNoArray[0]+tcNoArray[2]+tcNoArray[4]+tcNoArray[6]+tcNoArray[8]) * 8 )%10 == tcNoArray[10];
            return (exist1 && exist2 && exist3 );
        }

        return  false;
    }


    public Long getTcNo() {
        return tcNo;
    }

    public int[] getTcNoArray() {
        return tcNoArray;
    }
    public void setTcNo(Long tcNo) {
        this.tcNo = tcNo;
    }

    public void setTcNoArray(int[] tcNoArray) {
        this.tcNoArray = tcNoArray;
    }
}
