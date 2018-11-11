/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uepg.deinfo.linguagem;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author Ariangelo
 * @param <S>
 * @param <T>
 */
public class Formatted<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>>
{

    public enum Type
    {
        DF, NFC, NF, PERCENT
    }

    public final DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
    public final NumberFormat nfc = NumberFormat.getCurrencyInstance();
    public final NumberFormat nf = NumberFormat.getNumberInstance();

    private Type type;

    private int decimals = 2;

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public int getDecimals()
    {
        return decimals;
    }

    public void setDecimals(int decimals)
    {
        this.decimals = decimals;
    }

    @Override
    public TableCell<S, T> call(TableColumn<S, T> p)
    {
        TableCell<S, T> cell = new TableCell<S, T>()
        {
            @Override
            protected void updateItem(Object item, boolean empty)
            {
                super.updateItem((T) item, empty);
                if (item != null)
                {
                    switch (type)
                    {
                        case DF:
                            //setText(df);
                            break;
                        case NFC:
                            setText(nfc.format(item));
                            break;
                        case NF:
                            nf.setMinimumFractionDigits(decimals);
                            nf.setMaximumFractionDigits(decimals);
                            setText(nf.format(item));
                            break;
                        case PERCENT:
                            nf.setMinimumFractionDigits(decimals);
                            nf.setMaximumFractionDigits(decimals);
                            setText(nf.format(item) + " %");
                            break;
                        default:
                            setText(item.toString());
                    }
                } else
                {
                    setText("");
                }
            }
        };
        return cell;
    }
}
