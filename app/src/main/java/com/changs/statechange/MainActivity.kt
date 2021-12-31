package com.changs.statechange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.EditText
import com.changs.statechange.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val TAG = "StateChange"
    private lateinit var editText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.i(TAG,"onCreate")

        editText = binding.editText

        //



    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause")
        /*
        onPause에선 애니메이션과 같이 CPU를 지나치게 사용하는 작업을 중단시켜야하고
        아직 저장하지 않은 영속적 상태 데이터를 저장 단, 데이터베이스에 데이터를 저장하거나
        네트워크를 사용하는 작업을 피해야 한다 (시간이 오래 걸리는 작업들)
        */
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy")
    }

    /*
    Bundle 클래스는 키-값의 쌍으로 구성되는 데이터를 저장하는 역할을 한다. 키는 문자열 값이며,
    키와 연관된 값은 기본형 데이터 값이거나 또는 안드로이드 Parcelable 인터페이스를 구현하는 어떤 객체도 될 수 있다.
    Bundle 클래스는 다양한 타입을 갖는 키-값 데이터를 읽거나 쓰는 함수를 갖고 있다.
    여기서는 EditText 뷰의 텍트스 자동 저장을 못하게 했으므로 (android:saveEnabled="false")
    사용자가 EditText에 입력한 텍스트를 Bundle 객체에 저장했다가 나중에 복원해야 한다.
    */




    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"onSaveInstanceState")

        val userText = editText.text
        outState.putCharSequence("savedText",userText) //Bundle 객체의 putCharSequence() 함수를 사용해서 텍스트 값을 Bundle 객체에 저장할 수 있다.
        /*
        현재의 동적 상태 데이터가 저장될 수 있도록 액티비티가 소멸되기 전에 호출된다.
        여기서 동적 상태 데이터는 UI 데이터와 관련, 저장되어야 하는 상태 데이터를 갖는 Bundle 객체가 인자로 전달되며
        이 객체는 이후에 액티비티가 다시 시작될 때 onCreate와  onRestoreInstanceState 함수에 전달된다.
        동적 상태 데이터가 저장될 필요가 있다고 런타임이 판단할 경우에만 이 함수가 호출된다.
        저장된 동적 상태 데이터는 액티비티 생명주기 함수에서 복원될 수 있으며 이때 함수의 인자로 Bundle 객체가 전달된다.
        그리고 onCreate와 OnRestoreInstanceState 중 어떤 함수에서 복원할 것인지 선택하면 된다. 복원할 함수의 선택은 액티비티의 특성에 달렸다.
        예를 들어, 액티비티의 초기화 작업이 모두 수행된 후에 상태를 복원하는 것이 가장 좋다면 후자가 더 적합하다.
        여기서는 저장된 상태를 Bundle 객체로 부터 추출하기 위해 onRestoreInstanceState 함수에 복원 코드를 추가할 것이다. */
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG,"onRestoreInstanceState")

        /*
        상태 정보가 저장되었던 이전 액티비티 인스턴스로부터 액티비티 인스턴스가 다시 생성되어 시작될 때
        이전 상태를 포함하는 Bundle 객체가 인자로 전달되어 이 객체로 동적 데이터를 복원하면 된다.
        */

        val userText  = savedInstanceState.getCharSequence("savedText")
        editText.setText(userText)
    }
}