"""
Barry Martin
Lab 4/5
CS310
"""

def paras(fileName):
    f=open(fileName,'r') #Open file, read only
    para="" 
    for line in f: #Read file line by line
        if len(line)!=1: #Check for new paragraph
            line.strip() #remove whitespace
            end=line.find("#") #find first comment
            line=line[:end] #Slice out comment from string
            para+=line+"\n" #Add line to paragraph string
        else :
          yield para #Return paragraph
          para="" #Reset paragraph

def _parser():
  n= 0
  for para in paras("310st.txt"):
    print "\n--|",n,"|----------------------"
    print para
    print "<end>"
    n += 1

_parser()
