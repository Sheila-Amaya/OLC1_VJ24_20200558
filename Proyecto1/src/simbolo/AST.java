/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

/**
 *
 * @author eliza
 */
public class AST {
    public int nodoID = 0;
    public AST(){}
    public int getNewID() {
        return nodoID++;
    }
}