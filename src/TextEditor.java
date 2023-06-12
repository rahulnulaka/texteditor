import java.io.*;
import javax.swing.*;
import java.awt.*;  
import java.awt.event.*; 
import javax.swing.filechooser.*;
import javax.swing.border.*;
public class TextEditor implements ActionListener{
    //adding texteditor propertites
    JFrame frame;
    JMenuBar menubar;
    JTextArea textarea;
    
    JMenu file;
    //adding file menu items
    JMenuItem NewWindow;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    
    
    
    
    JMenu edit;
    //adding edit menuiytems
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem SelectAll;
    JMenuItem Close;
    TextEditor(){
        //intializing frame
        frame=new JFrame();
        //intializing menubar
        menubar=new JMenuBar();
        //intializing filemenu       
        file=new JMenu("file");
        //intializing filemenuitems
        NewWindow=new JMenuItem("new window");
        OpenFile=new JMenuItem("open file");
        SaveFile=new JMenuItem("save file");
        // adding actionlistener 
        NewWindow.addActionListener(this);
        OpenFile.addActionListener(this);
        SaveFile.addActionListener(this);
        //adding menuitems to filemenu
        file.add(NewWindow);
        file.add(OpenFile);
        file.add(SaveFile);
       //intializing edit menu
        edit=new JMenu("edit");
        //intializing edit menuitems
        Cut=new JMenuItem("cut");
        Copy=new JMenuItem("copy");
        Paste=new JMenuItem("paste");
        SelectAll=new JMenuItem("selectall");
        Close=new JMenuItem("close");
        //adding action listeners
        Cut.addActionListener(this);
        Copy.addActionListener(this);
        Paste.addActionListener(this);
        SelectAll.addActionListener(this);
        Close.addActionListener(this);
        //adding menuitems to edit menu
        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        edit.add(SelectAll);
        edit.add(Close);
        //adding menus to menubar
        menubar.add(file);
        menubar.add(edit);
        //intializing textarea
        textarea=new JTextArea();
        //creating object of jpanel
        JPanel panel=new JPanel();
        //setting borders of panel
        panel.setBorder(new EmptyBorder(5,5,5,5));
        //setting layout of the panel
        panel.setLayout(new BorderLayout(0,0));
        //adding textarea to the panel
        panel.add(textarea,BorderLayout.CENTER);
        //creating an object of scrollpane
        JScrollPane scrollpane=new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //adding scroll pane to panel        
        panel.add(scrollpane);
        //adding panel to frame
        frame.add(panel);
        //setting menubar to frame
        frame.setJMenuBar(menubar);
        //setting dimensions of the frame
        frame.setBounds(100,200,400,400);
        //creating title of the frame
        frame.setTitle("Text Editor");
        //making it visible
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent ev){
        //if the actionevent is cut
        if(ev.getSource()==Cut){
            //adding the cut functionality
            textarea.cut();
        }
        //if the actionevent is copy
        if(ev.getSource()==Copy){
            //adding the copy functionality
            textarea.copy();
        }
        //if the actionevent is paste
        if(ev.getSource()==Paste){
            //adding the paste functionality
            textarea.paste();
        }
        //if the action event is selectall
        if(ev.getSource()==SelectAll){
            //adding the selectall functionality
            textarea.selectAll();
        }
        //if the actionevent is close
        if(ev.getSource()==Close){
            //adding the close functionality
            System.exit(0);
        }
        //if the actionevent is newwindow
        if(ev.getSource()==NewWindow){
            //creating the fuctionality for newwindow
            TextEditor texteditor=new TextEditor();
        }
        //if the actionevent is openfile
        if(ev.getSource()==OpenFile){
            //selecting the filechoose 
            JFileChooser filechooser=new JFileChooser("c:");
            //adding the open dialog to the filechhoser
            int returnvalue=filechooser.showOpenDialog(null);
            //validating the opendialog
            if(returnvalue==JFileChooser.APPROVE_OPTION){
                //getting the file 
                File file=filechooser.getSelectedFile();
                //getting the filepath
                String filePath=file.getPath();
                try{
                    //reading the filepath
                    FileReader filereader=new FileReader(filePath);
                    //reading the content in the file
                    BufferedReader bf=new BufferedReader(filereader);
                    String intermediate="";
                    String output="";
                    //adding the content to the file by reading line by line to the output string
                    while((intermediate=bf.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //adding the content of output string to the textarea
                    textarea.setText(output);
                }
                catch(IOException filenotfound){
                    filenotfound.printStackTrace();
                }
            }
        }
        //if the actionevent is savefile
        if(ev.getSource()==SaveFile){
            //choosing the filechooser
            JFileChooser filechooser=new JFileChooser("c:");
            //adding save dialog to the filechooser
            int returnvalue=filechooser.showSaveDialog(null);
            //validatimng save dialog
            if(returnvalue==JFileChooser.APPROVE_OPTION){
                //creating a new file
                File file=new File(filechooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //creating a filewriter
                    FileWriter filewriter=new FileWriter(file);
                    //creating bufferwriter
                    BufferedWriter bf=new BufferedWriter(filewriter);
                    //adding to textarea
                    textarea.write(bf);
                    bf.close();
                }
                catch(IOException io){
                    io.printStackTrace();
                }
            }            
        }
    }
    public static void main(String[] args) throws Exception {
        //creating the object of the texteditor
        TextEditor texteditor=new TextEditor();
    }
}