package com.example.sql1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText editRoll,editName,editMarks;
Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShow;
SQLiteDatabase sqLDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editRoll=findViewById(R.id.editRollno);
        editName=findViewById(R.id.editName);
        editMarks=findViewById(R.id.editMark);
        btnAdd=findViewById(R.id.buttonAdd);
        btnModify=findViewById(R.id.buttonModify);
        btnDelete=findViewById(R.id.buttonDelete);
        btnView=findViewById(R.id.btnView);
        btnViewAll=findViewById(R.id.btnViewAll);
        btnShow=findViewById(R.id.btnShow);

        btnAdd.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShow.setOnClickListener(this);

        sqLDb=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE,null);
        sqLDb.execSQL("CREATE TABLE IF NOT EXISTS student(rollno VARCHAR,name VARCHAR,marks VARCHAR);");
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.buttonAdd:
                if (editRoll.getText().toString().trim().length() == 0 ||
                        editName.getText().toString().trim().length() == 0 ||
                        editMarks.getText().toString().trim().length() == 0)
                {
                    Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    showmsg("Error", "Invalid Input");
                    return;
                }

            sqLDb.execSQL("INSERT INTO student VALUES('"+editRoll.getText()+"','"+editName.getText()+
                    "','"+editMarks.getText()+"');");
            showmsg("Success", "Record added");
            break;
            case R.id.buttonDelete: {
                if (editRoll.getText().toString().trim().length() == 0) {
                    showmsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c1 = sqLDb.rawQuery("SELECT * FROM student WHERE rollno='" + editRoll.getText() + "'", null);
                if (c1.moveToFirst()) {
                    sqLDb.execSQL("DELETE FROM student WHERE rollno='" + editRoll.getText() + "'");
                    showmsg("Success", "Record Deleted");
                } else {
                    showmsg("Error", "Invalid Rollno");
                }
                clearText();
            }

            break;

            case R.id.buttonModify:


                if (v == btnModify) {
                    if (editRoll.getText().toString().trim().length() == 0) {
                        showmsg("Error", "Please enter Rollno");
                        return;
                    }
                    Cursor c2 = sqLDb.rawQuery("SELECT * FROM student WHERE rollno='" + editRoll.getText() + "'", null);
                    if (c2.moveToFirst()) {
                        sqLDb.execSQL("UPDATE student SET name='" + editName.getText() + "',marks='" + editMarks.getText() +
                                "' WHERE rollno='" + editRoll.getText() + "'");
                        showmsg("Success", "Record Modified");
                    } else {
                        showmsg("Error", "Invalid Rollno");
                    }
                    clearText();
                }


                break;
            case R.id.btnView:

                if (v == btnView) {
                    if (editRoll.getText().toString().trim().length() == 0) {
                        showmsg("Error", "Please enter Rollno");
                        return;
                    }
                    Cursor c = sqLDb.rawQuery("SELECT * FROM student WHERE rollno='" + editRoll.getText() + "'", null);
                    if (c.moveToFirst()) {
                        editName.setText(c.getString(1));
                        editMarks.setText(c.getString(2));
                    } else {
                        showmsg("Error", "Invalid Rollno");
                        clearText();
                    }
                }


                break;
            case R.id.btnViewAll:

                if (v == btnViewAll) {
                    Cursor c3 = sqLDb.rawQuery("SELECT * FROM student", null);
                    if (c3.getCount() == 0) {
                        showmsg("Error", "No records found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (c3.moveToNext()) {
                        buffer.append("Rollno: " + c3.getString(0) + "\n");
                        buffer.append("Name: " + c3.getString(1) + "\n");
                        buffer.append("Marks: " + c3.getString(2) + "\n\n");
                    }
                    showmsg("Student Details", buffer.toString());
                }

                    break;

                    case R.id.btnShow:
                      showmsg("Developed By-", "komal");
                        break;
                }
        }

        private void clearText()
        {
            editRoll.setText("");
            editName.setText("");
            editMarks.setText("");

        }
        private void showmsg (String title, String msg){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setCancelable(true);
            alertDialog.setTitle(title);
            alertDialog.setMessage(msg);
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.show();
        }
    }