package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    // DEBUG
    public boolean checkDrawTime = false;
    
    
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState) {

            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if(gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }

                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if(gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }

                if(code == KeyEvent.VK_ENTER) {
                    if(gp.ui.commandNum == 0) {
                        gp.gameState = gp.playState;
                        //gp.playMusic(0);
                    }
                    if(gp.ui.commandNum == 1) {
                    }
                    if(gp.ui.commandNum == 2) {
                        System.exit(0);
                    }
                }
            }
        }

        // PLAY STATE
        else if (gp.gameState == gp.playState) {

            if (code == KeyEvent.VK_W) upPressed = true;

            if (code == KeyEvent.VK_S) downPressed = true;

            if (code == KeyEvent.VK_A) leftPressed = true;

            if (code == KeyEvent.VK_D) rightPressed = true;

            if (code == KeyEvent.VK_P) gp.gameState = gp.pauseState;

            if (code == KeyEvent.VK_ENTER) enterPressed = true;

            if(code == KeyEvent.VK_ESCAPE) gp.gameState = gp.optionsState;

            if (code == KeyEvent.VK_T) {
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                } else if (checkDrawTime = true) {
                    checkDrawTime = false;
                }
            }
        }

        // PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }

        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            if(code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        // OPTIONS STATE
        else if (gp.gameState == gp.optionsState) optionState(code);

        // GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) gameOverState(code);

        // THANKS STATE
        else if (gp.gameState == gp.thanksState) thanksState(code);
    }

    public void optionState(int code) {

        if (code == KeyEvent.VK_ESCAPE) gp.gameState = gp.playState;
        if (code == KeyEvent.VK_ENTER) enterPressed = true;

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }

        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            //gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            //gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    //gp.playSE(9);
                }

                if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    //gp.playSE(9);
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    //gp.playSE(9);
                }

                if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    //gp.playSE(9);
                }
            }
        }
    }

    public void gameOverState(int code) {

        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) gp.ui.commandNum = 1;
            //gp.playSE(9);
        }
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) gp.ui.commandNum = 0;
            //gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    public void thanksState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
