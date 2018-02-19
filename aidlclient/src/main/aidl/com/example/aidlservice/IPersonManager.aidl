// IPersonManager.aidl
package com.example.aidlservice;

// Declare any non-default types here with import statements
import com.example.aidlservice.Person;
interface IPersonManager {
    List<Person> getPersons();
    void addPerson(in Person person);
    /*
     in - 输入
     out - 输出
     inout - 输入输出
     */
}
