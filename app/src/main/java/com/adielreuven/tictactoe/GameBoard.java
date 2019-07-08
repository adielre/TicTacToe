package com.adielreuven.tictactoe;

public class GameBoard
{
    static final int X_WON = 1;
    static final int O_WON = -1;
    static final int NO_WIN = 0;

    private char [][] board;
    private char currentPlayerMark;

    private int [] winningStrick;
    private int Xscore, Oscore, draws;
    private int drawCheck;

    public GameBoard()
    {
        board = new char[3][3];
        winningStrick = new int [3];
        currentPlayerMark = 'X';
        Xscore = 0;
        Oscore = 0;
        draws = 0;
        drawCheck = 0;
        createNewBoard();
    }

    public int getXscore()
    {
        return Xscore;
    }
    public int getOscore()
    {
        return Oscore;
    }
    public int getDraws()
    {
        return draws;
    }
    public void setXscore(int num) { Xscore=num; }
    public void setOscore(int num)
    {
        Oscore=num;
    }
    public void setDraws(int num) {draws=num;}

    public char getcurrentPlayerMark()
    {
        return currentPlayerMark;
    }

    public void createNewBoard()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                board[i][j] = ' ';

            }
        }
    }

    public int [] getWinningStrick(char sign)
    {
        for (int i = 0; i < 3; i++)
        {
            if ( board[i][0] == sign && board[i][1] == sign && board[i][2] == sign ) // rows
            {
                if(i==0)
                {
                    winningStrick[0]=0;
                    winningStrick[1]=1;
                    winningStrick[2]=2;
                }
                else if(i==1)
                {
                    winningStrick[0]=3;
                    winningStrick[1]=4;
                    winningStrick[2]=5;
                }
                else
                {
                    winningStrick[0]=6;
                    winningStrick[1]=7;
                    winningStrick[2]=8;
                }
                return winningStrick;
            }
            else if( board[0][i] == sign && board[1][i] == sign && board[2][i] == sign ) // columns
            {
                if(i==0)
                {
                    winningStrick[0]=0;
                    winningStrick[1]=3;
                    winningStrick[2]=6;
                }
                else if(i==1)
                {
                    winningStrick[0]=1;
                    winningStrick[1]=4;
                    winningStrick[2]=7;
                }
                else
                {
                    winningStrick[0]=2;
                    winningStrick[1]=5;
                    winningStrick[2]=8;
                }
                return winningStrick;
            }
        }
        if( board[0][0] == sign && board[1][1] == sign && board[2][2] == sign ) // diagonal = \
        {
            winningStrick[0]=0;
            winningStrick[1]=4;
            winningStrick[2]=8;
            return winningStrick;
        }

        else  // diagonal = /
        {
            winningStrick[0]=2;
            winningStrick[1]=4;
            winningStrick[2]=6;
            return winningStrick;
        }
    }

    public void turnPlayer(int row, int col)
    {
        if( board[row][col] == ' ')
            board[row][col] = currentPlayerMark;
    }

    public void changePlayer()
    {
        if (currentPlayerMark == 'X')
            currentPlayerMark = 'O';

        else
            currentPlayerMark = 'X';
    }


    public int isGameOver(char sign)
    {
        if(  checkForWin(sign) == true  )
        {
            if( sign == 'X' ) // X won
            {
                Xscore++;
                return X_WON;
            }
            else if( sign == 'O' ) // O won
            {
                Oscore++;
                return O_WON;
            }
        }
        return NO_WIN; // case that no win
    }

    // Loop through rows, columns and diagonals and see if any are winners.

    private boolean checkForWin(char sign)
    {
        for (int i = 0; i < 3; i++)
        {
            if ( board[i][0] == sign && board[i][1] == sign && board[i][2] == sign ) // rows
                return true;

            else if( board[0][i] == sign && board[1][i] == sign && board[2][i] == sign ) // columns
                return true;

        }
        if( board[0][0] == sign && board[1][1] == sign && board[2][2] == sign ) // diagonal = \
            return true;

        else if( board[0][2] == sign && board[1][1] == sign && board[2][0] == sign ) // diagonal = /
            return true;

        return false;
    }

    // Loop through all cells of the board and if one is found to be empty then return false.
    // Otherwise the board is full.
    public boolean isDraw()
    {
        drawCheck++;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if( board[i][j] ==	' ')
                    return false;
            }
        }

        if(drawCheck%2==0)
            draws++;

        return true;
    }

}// end GameBoard class
