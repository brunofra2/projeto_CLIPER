package bac.com.br.hibernate.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Andre Luiz
 */
public class TextDocument extends PlainDocument {

    private int tamanhoMax = 0;

    public void SetTamanho(int tamanho) {
        tamanhoMax = tamanho;
    }

    /**
     * Cria o validador com o tamanho m�ximo de 4000
     */
    public TextDocument() {
        super();

    }

    /**
     * Cria o validador com o tamanho definido no par�metro
     *
     * @param tamanho Tamanho m�ximo da cadeia de caracteres
     */
    public TextDocument(int tamanho) {
        super();
        tamanhoMax = tamanho;
    }

    /**
     * A cada tecla pressionada valida a tecla verifica se n�o est� no m�ximo
     * que o campo pode aguentar.
     *
     * @see javax.swing.text.Document#insertString(int, java.lang.String,
     * javax.swing.text.AttributeSet)
     */
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

        if (str == null) {
            return;
        }

        String oldString = getText(0, getLength());
        String newString = oldString.substring(0, offs) + str + oldString.substring(offs);

        if (newString.length() > tamanhoMax) {
            super.insertString(offs, "", a);
        } else {
            // Repassa para o pai.  
            super.insertString(offs, str, a);
        }

    }
}
