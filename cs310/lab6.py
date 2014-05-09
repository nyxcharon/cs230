"""
Barry Martin
Lab 6

"""

class Edge:
    def __init__(self,here,there,txt):
      self.description = txt     # why am i making this jump? 
      self.here        = here    # where do i start
      self.there       = there   # where to i end
      self.here.out   += [self]  # btw, tell here that they can go there

    def __repr__(self):
      return "E(" + self.here.name + " > " + self.there.name + ")"


class Node:
  end   = "."
  start = "!"

  def __init__(self,g,id,name,stop=False,start=False):
      self.id = id
      self.graph = g          # where do i live?
      self.name = name        # what is my name?
      self.description = ""   # tell me about myself
      self.stop = stop        # am i a stop node?
      self.start = start      # am i a start node?
      self.out = []           # where do i connect to

  def also(self,txt):
      "adds text to description"
      sep = "\n" if self.description else ""
      self.description += sep + txt

  def __repr__(self):
      return "N( :id " + str(self.id) + \
             "\n   :name " + self.name + \
             "\n   :about '" + self.description + "'" + \
             "\n   :out " + str(self.out) + ") "

class Graph:
    def __init__(self):
      self.nodes = []    # nodes, stored in creation order
      self.keys  = {}    # nodes indexed by name
      self.m = None      # adjacency matrix
      self.mPrime = None # transitive closure matrix

    def node(self,name):
      "returns a old node from cache or a new node"
      if not name in self.keys:
        self.keys[name] = self.newNode(name)
      return self.keys[name]

    def newNode(self,name):
      " create a new node"
      id = len(self.nodes) 
      tmp = Node(self,id,name)
      tmp.start = Node.start in name
      tmp.end   = Node.end   in name
      self.nodes += [tmp]
      return tmp

def paras(filename):
    f=open(filename,'r') #Open file, read only                                                                                                                                              
    para=""                                                                                                                                                                                                
    for line in f: #Read file line by line     
        if len(line)!=1: #Check for new paragraph
            line.strip() #remove whitespace
            end=line.find("#") #find first comment                                                                                                                        
            line=line[:end] #Slice out comment from string
            para+=line+"\n"  #Add line to paragraph string                                                           
        else :                                                                                                                                                                                             
          yield para #Return paragraph                                            
          para="" #Reset paragraph   

def graph(f) :
  g = Graph()
  for para in paras(f):
    lines = para.split("\n")  # split para into lines
    start=False
    end=False 
    if lines[0].find(".")!=-1:
       end=False 
       lines[0]=lines[0].replace(".","")
    elif lines[0].find("!")!=-1:
       start=True
       lines[0]=lines[0].replace("!","")
    here  = g.node(lines[0]) # the current node
    if start:
        here.start=True
    elif end:
        here.end=True
    for i in xrange(1,len(lines)):  # for all the other lines
      if not lines[i].startswith(">"):
         here.description=lines[1]              # update heres' description
      else:
         words       = lines[i].split(" ")           # split a goto line
         destination = words[1] # where is the pointyLine sayign is our destination
         txt         = lines[i].replace(">","").replace(words[1],"",1) # what is the explanation text on the line
         there =  g.node(destination)          # where are we going?
         Edge(here,there,txt)          # connect here to there
  return g

print graph("stagetext.txt")
