package com.example.littlelemon.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField

import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.R

@Composable
fun OnboardingScreen(onNavigateToHome: (User) -> Unit) {



    var firstName by remember {
        mutableStateOf("")
    }
    var firstNameError by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var lastNameError by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var emailError by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }

    fun handleRegistration() {
        if(firstName.isBlank()){
            firstNameError = "First Name is required"
            isError = true

        }
        if(lastName.isBlank()){
            lastNameError = "Last Name is required"
            isError = true
        }
        if(email.isBlank()){
            emailError = "Email Name is required"
            isError = true
        }
        if(isError)
            return

        val user = User(firstName, lastName, email)

        onNavigateToHome(user)

    }
    Column(
        Modifier
            .width(IntrinsicSize.Max)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(500.dp)
                .height(100.dp)
                .padding(25.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.primary))
                .padding(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Let's get to know you",
                color     = Color.White,
                fontSize  = 24.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp))
        {
            Text("Personal Information", color = colorResource(id = R.color.primary), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(all= 5.dp))
            CustomTextField(label = "First Name", onChangeValue = { firstName = it }, value = firstName, isError = isError, errorMessage = firstNameError)

            Spacer(modifier = Modifier.padding(all= 5.dp))
            CustomTextField(label = "Last Name", onChangeValue = { lastName = it }, value = lastName,isError = isError, errorMessage = lastNameError)

            Spacer(modifier = Modifier.padding(all= 5.dp))
            CustomTextField(label = "Email", onChangeValue = { email = it }, value = email,isError = isError, errorMessage = emailError)
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Register",
                textAlign = TextAlign.Center, // Centers horizontally
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp, // You can adjust the border thickness
                        color = Color.Black, // Black border
                        shape = RoundedCornerShape(20.dp) // Apply radius to the border
                    )
                    .height(60.dp)
                    .background(
                        color = colorResource(id = R.color.secondary),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .clickable {
                        handleRegistration()
                    }

            )



        }







    }

}



@Composable
fun CustomTextField(label:String,onChangeValue:(String) -> Unit,value:String, isError: Boolean,errorMessage:String){
    Column{
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        OutlinedTextField(value = value, onValueChange = onChangeValue,


            colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor    = Color(0xFFf4c542),
            unfocusedBorderColor  = Color.Gray,
            errorBorderColor      = Color.Red,
            disabledBorderColor   = Color.LightGray,
            cursorColor           = Color(0xFFf4c542) ,

        ),

            isError = isError,
            

            modifier = Modifier
            .fillMaxWidth(),
            label = {Text(label)}
        )
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Light, color = Color.Red)
    }
}


/*@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding()
}*/