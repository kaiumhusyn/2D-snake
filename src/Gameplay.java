import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
    private ImageIcon titleIc;

    private int snakeXlen[] = new int[750];
    private int snakeYlen[] = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon leftMouth;
    private ImageIcon rightMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private int snakeLen = 3;
    private int moves = 0;

    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeImage;
    int x = 200;
    int y = 500;



    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g) {

        x += 25;
        if(x >650) x = 25;


        if (moves == 0) {
            snakeXlen[2] = 50;
            snakeXlen[1] = 75;
            snakeXlen[0] = 100;

            snakeYlen[2] = 100;
            snakeYlen[1] = 100;
            snakeYlen[0] = 100;


        }


        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        //draw the title image
        titleIc = new ImageIcon("snaketitle.png");
        titleIc.paintIcon(this, g, 25, 11);
        //border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        rightMouth = new ImageIcon("rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXlen[0], snakeYlen[0]);



        for (int i = 0; i < snakeLen; ++i) {
            if (i == 0 && right) {
                rightMouth = new ImageIcon("rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }

            if (i == 0 && left) {
                leftMouth = new ImageIcon("leftmouth.png");
                leftMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }

            if (i == 0 && down) {
                downMouth = new ImageIcon("downmouth.png");
                downMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }

            if (i == 0 && up) {
                upMouth = new ImageIcon("upmouth.png");
                upMouth.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }

            if (i != 0) {
                snakeImage = new ImageIcon("snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXlen[i], snakeYlen[i]);
            }


        }

        drawOpponentInstance(g,false,true,false,false,x,y);
        g.dispose();


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("r:" + right + " l:" + left + " u:" + up + " d:" + down);
        for (int i = 0; i < snakeLen; ++i) {
            System.out.print(snakeXlen[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < snakeLen; ++i) {
            System.out.print(snakeYlen[i] + " ");
        }


        // timer.start();
        if (right) {
            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeYlen[r + 1] = snakeYlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeXlen[r] = snakeXlen[r] + 25;

                } else {
                    snakeXlen[r] = snakeXlen[r - 1];

                }
                if (snakeXlen[r] > 850) {
                    snakeXlen[r] = 25;

                }

            }
            repaint();


        }
        if (left) {

            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeYlen[r + 1] = snakeYlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeXlen[r] = snakeXlen[r] - 25;

                } else {
                    snakeXlen[r] = snakeXlen[r - 1];

                }
                if (snakeXlen[r] < 25) {
                    snakeXlen[r] = 850;

                }

            }
            repaint();


        }
        if (up) {

            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeXlen[r + 1] = snakeXlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeYlen[r] = snakeYlen[r] - 25;

                } else {
                    snakeYlen[r] = snakeYlen[r - 1];

                }
                if (snakeYlen[r] < 75) {
                    snakeYlen[r] = 625;

                }

            }
            repaint();


        }
        if (down) {

            for (int r = snakeLen - 1; r >= 0; r--) {
                snakeXlen[r + 1] = snakeXlen[r];

            }

            for (int r = snakeLen; r >= 0; r--) {
                if (r == 0) {
                    snakeYlen[r] = snakeYlen[r] + 25;

                } else {
                    snakeYlen[r] = snakeYlen[r - 1];

                }
                if (snakeYlen[r] > 625) {
                    snakeYlen[r] = 75;

                }

            }
            repaint();


        }

        System.out.println();


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                moves++;
                right = true;
                if (left) {
                    right = false;
                    left = true;
                }
                up = false;
                down = false;
                break;
            case KeyEvent.VK_LEFT:
                moves++;
                left = true;
                if (right) {
                    right = true;
                    left = false;
                }
                up = false;
                down = false;
                break;
            case KeyEvent.VK_UP:
                moves++;
                up = true;
                if (down) {
                    up = false;
                    down = true;
                }
                left = false;
                right = false;
                break;

            case KeyEvent.VK_DOWN:

                moves++;
                down = true;
                if (up) {
                    up = true;
                    down = false;
                }
                left = false;
                right = false;
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void drawOpponentInstance(Graphics g,boolean l,boolean r,boolean u,boolean d,int x,int y) {

        if(l){

            rightMouth = new ImageIcon("rightmouth.png");
            rightMouth.paintIcon(this, g, x, y);

        }
        if(r){
            rightMouth = new ImageIcon("rightmouth.png");
            rightMouth.paintIcon(this, g, x, y);

        }
        if(u){
            rightMouth = new ImageIcon("rightmouth.png");
            rightMouth.paintIcon(this, g, x, y);

        }
        if(d){
            rightMouth = new ImageIcon("rightmouth.png");
            rightMouth.paintIcon(this, g, x, y);

        }
        ImageIcon snakeImage = new ImageIcon("snakeimage.png");
        snakeImage.paintIcon(this,g,x-25,y);
        snakeImage.paintIcon(this,g,x-50,y);
        g.dispose();

    }



}
