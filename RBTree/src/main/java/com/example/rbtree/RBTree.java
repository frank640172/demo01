package com.example.rbtree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.jvm.hotspot.utilities.RBNode;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RBTree<K extends  Comparable<K>,V> {
    private  static final boolean RED = false;
    private  static final boolean BLACK = true;

    //记录红黑树的root节点
    private RBNode root;




    /*
   做双向的
   此外还要判读p是否有父节点，因为父节点对应的子节点也会发生变化
     */
    private  void leftRotate(RBNode p){
         if(p == null) return;
         RBNode parent = p.parent;
         RBNode q = p.right;
         if(parent != null){
             q.parent = parent;
             //根据大小来判断左子节点还是右子节点，我猜的
             if(p.parent.left == p) {
                 parent.left = q;
             }else {
                 parent.right = q;
             }
         }else{
             //如果没有父，则pr直接为父亲
             root = q;
         }
        p.right = q.left;
         if(p.right!=null) {
             p.right.parent = p;
         }
        q.left = p;
        p.parent = q;
    }



    private  void rightRotate(RBNode p){
        if(p == null) return;
        RBNode parent = p.parent;
        RBNode q = p.left;
        if(parent != null){
            q.parent = parent;
            //根据大小来判断左子节点还是右子节点，我猜的
            if(p.parent.left == p) {
                parent.left = q;
            }else {
                parent.right = q;
            }
        }else{
            //如果没有父，则pr直接为父亲
            root = q;
        }
        p.left = q.right;

        if(p.left!=null) {
            p.left.parent = p;
        }

        q.right = p;
        p.parent = q;
    }


    public  void put(K key ,V val){

        RBNode t = root;
        if(t == null) {
             t = new RBNode(key,val == null? key:val,null);
             return;
        }
        //找到插入的位置，找到新增节点的位置
        RBNode parernt;
        int cmp;
        do{
              parernt = t;
              //强制类型转换
              cmp = key.compareTo((K) t.key) ;
              if(cmp > 0){//key比当前的根大
                  t = parernt.right;
              }else if(cmp < 0){
                  t =  parernt.left;
              }else{
                //用插入节点的值去覆盖掉之前的节点
                  t.setValue(val);
                  return;
              }
        }while(t!=null);

        // 将新节点添加到父节点的子节点中
       RBNode node = new RBNode(key,val,parernt);
       cmp = key.compareTo((K) parernt.key);
       if(cmp < 0) parernt.left = node;
       else parernt.right =node;
       //旋转变色来进行调整
        fixAfterPut(node);
    }

    //找到指定节点的前驱节点,删除要用到该函数，如果
    private RBNode predecessor(RBNode node){
         if(node == null) return null;
         if(leftOf(node) !=null){
             node = node.left;
             while(node!=null && node.right!=null)  node = node.right;
         }else{
             //红黑树的删除用不到这个
             //往上找，找到第一个父节点的右儿子为当前的祖先
                RBNode p = node.parent;
                RBNode child = node;
                while(p!=null && rightOf(p)!=child){
                    child = p;
                    p = parentOf(p);
                }
                return p;
         }
         return node;
    }

    //找到指定节点的前驱节点,删除要用到该函数，如果
    private RBNode successor(RBNode node){
        if(node == null) return null;
        if(rightOf(node) !=null){
            node = rightOf(node);
            while(node!=null && node.left!=null)  node = node.left;
        }else{
            //红黑树的删除用不到这个
            //往上找，找到第一个父节点的右儿子为当前的祖先
            RBNode p = node.parent;
            RBNode child = node;
            while(p!=null && leftOf(p)!=child){
                child = p;
                p = parentOf(p);
            }
            return p;
        }
        return node;
    }


    private  boolean colorof(RBNode node){
        return node == null? BLACK : node.color;
    }

    private  RBNode parentOf(RBNode node){
        return node == null ? null : node.parent;
    }

    private  RBNode leftOf(RBNode node){
        return node !=null ? node.left : null;
    }

    private  RBNode rightOf(RBNode node){
        return node != null ? node.right: null;
    }

    private  void setColor(RBNode node,boolean color){
        if(node != null){
            node.color = color;
        }
    }

    /*
    删除节点
       节点删除（普通二叉树）
       删除后调整
     */
    public V remove(K key){
           //根据需要删除的key找到对应的Node节点
        RBNode node =getNode(key);
        if(node == null){
            return null;
        }
        V oldValue = (V) node.getValue();
        deleteNode(node);
        return oldValue;

    }

    /*
    删除节点：1删除节点2。调整
    删除节点当作一棵普通的二叉树
        1.删除叶子节点，直接删除
        2.删除节点有一个子节点，用子节点来替代被删除的节点
        3.删除节点右两个子节点，则找到该节点的后继节点之后将值进行swap，之后删除后继节点
         将情况三反复递归，最后转换为情况1或者2
      先处理情况3，之后2，之后1
     */
    private void deleteNode(RBNode node) {
//这个函数执行一次即可，为了保险弄成这个
         if(leftOf(node) != null && rightOf(node) != null){
             RBNode pre  = predecessor(node);
             node.key =  pre.key ;
             node.value = pre.value;
             node = pre;
         }

        //得到替换节点
        RBNode replacement  = node.left != null? node.left:node.right;
         if(replacement != null){
             //双向关系的处理
             replacement.parent = node.parent;
             if(node.parent == null) {//要删除的节点为根节点,没这个会报错
                 root = replacement;
             }else if(parentOf(node).left == node) parentOf(node).left = replacement;
             else parentOf(node).right = replacement;

             //要删除的node节点 GC掉
             node.left =node.right = node.parent = null;

             if(node.color == BLACK){
                fixAfterRemove(replacement);
             }
         }else if(node.parent == null){
             root =null;
         }
         {//直接删除叶子节点
             //如果先删除了就没有节点了，所以要先进行调整
             if(node.color == BLACK){
                 fixAfterRemove(node);
             }
                if(parentOf(node) != null){
                     if(parentOf(node).left == node)
                         parentOf(node).left = null;
                     else parentOf(node).right = null;
                }
                node.parent =null;
         }

    }

    //删除后做调整
    /*
    1 删除3，4结点
    2 删除2结点自己搞不定，需要兄弟节点借
      父亲下来，兄弟节点找一个接单替换父节点
     3删除2节点，自己搞不定，需要兄弟借，兄弟不借
     */
    private void fixAfterRemove(RBNode e)
    {
        if(e!=root && colorof(e) == BLACK){
              //判断e是父节点的左儿子还是右儿子
              if(leftOf(parentOf(e)) == e){
                  //找到兄弟节点
                   RBNode r = rightOf(parentOf(e));
                 //判断找到的是不是真的兄弟节点
                  if(colorof(r) == RED){
                       //需要做一次变色和旋转
                      setColor(r,BLACK);
                      setColor(parentOf(e),RED);
                      leftRotate(parentOf(e));
                      r = parentOf(e).right;
                  }

                  //兄弟节点不借
                  if(colorof(leftOf(r))==BLACK && colorof(rightOf(r))==BLACK){

                  }else
                  {
                      //找到兄弟节点借
                      //如果兄弟节点的子节点是其左子节点，则需进行变色，完成右转
                      if(colorof(rightOf(r)) == BLACK){
                          setColor(r,RED);
                          setColor(r.left,BLACK);
                          rightRotate(r);
                          r = rightOf(parentOf(e));
                      }
                  }

              }else{

              }
        }
        //情况1这里因为做这个操作必须是删除的节点为黑色才做，需要变色为黑色
        setColor(e,BLACK);
    }



    /*
    根据key找到node节点
     */
    private RBNode getNode(K key) {
         RBNode r = root;
         while(r!= null){
             int cnt =key.compareTo((K)r.key);
             if(cnt > 0) r = rightOf(r);
             else if(cnt < 0) r = leftOf(r);
             else break;
         }
        return r;
    }


    /*
        调整插入节点后的操作
       红黑树：
       对应二节点，直接插入
       对应3节点插入到上黑下红的情况，并且插入的是红色节点下方 需要变色加旋转
       四节点：新增红，爷爷为黑色，父亲和叔叔都是红色，则需要变色+旋转

     */
    public  void fixAfterPut(RBNode<K,Object> x){
           x.setColor(RED);
           if(x == null) return;
           if(x.parent.color == BLACK) return;//不用调整了
           if(x == root) x.setColor(BLACK);

           //先判断是不是直线还是折线
           if(parentOf(x)== leftOf(parentOf(parentOf(x)))){
               //如果当前的父节点为祖父节点的左节点
               //之后再根据有没有叔叔节点进行依据在进行划分
               RBNode uncle  = rightOf(parentOf(parentOf(x)));
               if(colorof(uncle) == RED){
                   //变色加递归
                   //父亲和叔叔变成黑色，爷爷变为红色
                    setColor(parentOf(x),BLACK);
                    setColor(uncle,BLACK);
                    setColor(parentOf(parentOf(x)),RED);
                    x = parentOf(parentOf(x));
                    fixAfterPut(x);
               }else{
                   //如果插入节点是父节点的右节点，则做左旋
                  if(rightOf(parentOf(x)) == x){
                      x = parentOf(x);
                      leftRotate(parentOf(x));
                  }
                  //父亲变黑，爷爷变红
                     setColor(parentOf(x),BLACK);
                     setColor(parentOf(parentOf(x)),RED);
                     rightRotate(parentOf(parentOf(x)));

               }

           }else{//如果当前的父节点为祖父节点的右节点

               RBNode uncle  = leftOf(parentOf(parentOf(x)));
               if(colorof(uncle) == RED){
                   //变色加递归
                   //父亲和叔叔变成黑色，爷爷变为红色
                   setColor(parentOf(x),BLACK);
                   setColor(uncle,BLACK);
                   setColor(parentOf(parentOf(x)),RED);
                   x = parentOf(parentOf(x));
                   fixAfterPut(x);
               }else{
                   //如果插入节点是父节点的右节点，则做左旋
                   if(rightOf(parentOf(x)) == x){
                       x = parentOf(x);
                       rightRotate(parentOf(x));
                   }
                   //父亲变黑，爷爷变红
                   setColor(parentOf(x),BLACK);
                   setColor(parentOf(parentOf(x)),RED);
                   leftRotate(parentOf(parentOf(x)));

               }

           }

    }

    //红黑树的节点
    static  class RBNode<K extends Comparable<K>,V>{
        private  RBNode parent;
        private  RBNode left;
        private  RBNode right;
        private  boolean color;
        private  K key;
        private  V value;

        public RBNode(K key, V val, RBNode parent) {
            this.key = key;
            this.value = val;
            this.parent = parent;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


}
