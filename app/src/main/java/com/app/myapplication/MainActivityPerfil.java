package com.app.myapplication;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;


import herramientas.Usuario;

public class MainActivityPerfil extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText etNombre;
    private ImageView ivEditAvatar,ivEditNombre, ivAvatar, ivPremium;
    private TextView txNumeroCervezas;
    private String correo;
    private boolean edNombre = false, edAvatar = false;
    private Dialog dialog;
    private Usuario usuario, userAux;
    private InterstitialAd mInterstitialAd;
    //private RewardedVideoAd mRewardedVideoAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_perfil);

        //Coger datos del Intent anterior
        correo = getIntent().getStringExtra("correo");
        Toast.makeText(this, correo, Toast.LENGTH_LONG).show();


        //Instancia Usuario
        usuario = new Usuario(1,"Lupita", "Sara@yopmail.com", 0, "S", 0, 4);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        /*
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

         */


        //Recibir Objeto Usuario
        //Asignar Valores
        SocketCliente cliente;
        cliente = new SocketCliente(usuario);
        cliente.start();


        try {

            Thread.sleep(2700);

        } catch (InterruptedException e) {

        }

        userAux = cliente.getUsuario();
        Toast.makeText(this, userAux.toString(), Toast.LENGTH_LONG).show();



        //Instancia Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //TextView
        txNumeroCervezas = findViewById(R.id.textViewNumeroCervezas);

        //ImageView
        ivEditAvatar = findViewById(R.id.imageViewEditAvatar);
        ivEditNombre = findViewById(R.id.imageViewEditName);
        ivAvatar = findViewById(R.id.imageViewAvatarProfile);
        ivPremium = findViewById(R.id.imageViewPremiumDisponible);

        //EditText
        etNombre = findViewById(R.id.editTextNombreProfile);
        etNombre.setFocusable(false);
        etNombre.setEnabled(false);


        etNombre.setText(userAux.getNombre());
        txNumeroCervezas.setText(""+userAux.getCervezas());


        switch (userAux.getAvatar()){
            case 0: ivAvatar.setImageResource(R.drawable.avatar_cero);
                break;
            case 1: ivAvatar.setImageResource(R.drawable.avatar_uno);
                break;
            case 2: ivAvatar.setImageResource(R.drawable.avatar_dos);
                break;
            case 3: ivAvatar.setImageResource(R.drawable.avatar_tres);
                break;
            case 4: ivAvatar.setImageResource(R.drawable.avatar_cuatro);
                break;
            case 5: ivAvatar.setImageResource(R.drawable.avatar_cinco);
                break;
            default: ivAvatar.setImageResource(R.drawable.avatar_cero);
                break;
        }

        if (userAux.getAds().equals("S")){
            ivPremium.setImageResource(R.drawable.no_crown);
        }else{
            ivPremium.setImageResource(R.drawable.crown);
        }

    }


    //Metodo mostrar boton volver
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil, menu);
        return true;
    }

    //Metodo agregar acciones a nuestros botones
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnMinigames) {

            Intent intent = new Intent(this, MainActivity.class);
            int dado = (int)(Math.random()*6+90);
            String dadoAux  = ""+dado;
            intent.putExtra("correo", correo);
            intent.putExtra("dado", dadoAux);
            startActivity(intent);
            finish();

        }

        if (id == R.id.mnBorrarCuenta) {

            usuario.setAuxSeleccion(3);
            Thread borrarUsuario;
            borrarUsuario = new SocketCliente(usuario);
            borrarUsuario.start();

        }
        if (id == R.id.mnLogOut) {

            firebaseAuth.signOut();
            Intent intent = new Intent(this, MainActivityLogin.class);
            startActivity(intent);
            //Finalizar Activity
            finish();
        }

        if (id == R.id.mnPremium) {

            dialogTips();
        }

        return true;
    }


    public void dialogTips(){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_paypremium);
        //Button btnpago = findViewById(R.id.btnPayment);
        dialog.show();

    }



    public void payment(View view){

        Toast.makeText(this, "Pago Realizado", Toast.LENGTH_LONG).show();
        dialog.dismiss();
        usuario.setAds("N");

        /*
        Thread modificarPremiumUsuario;
        modificarPremiumUsuario = new SocketCliente(usuario);
        modificarPremiumUsuario.start();
         */
    }


    public void editarNombre(View view){

        if (!edAvatar){

            etNombre.setFocusable(true);
            etNombre.setEnabled(true);
            ivEditNombre.setBackgroundColor(Color.GREEN);
            edAvatar = true;

        }else{

            etNombre.setFocusable(false);
            etNombre.setEnabled(false);
            ivEditNombre.setBackgroundColor(Color.GRAY);
            edAvatar = false;

        }
    }

    public void seleccionarAvatarDialog(View view){

        ivEditAvatar.setBackgroundColor(Color.GREEN);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_select_avatar);
        dialog.show();

    }

    @SuppressLint("NonConstantResourceId")
    public void seleccionarAvatar(View view){

        int id = view.getId();

        switch (id){
            case R.id.avatar_1x1:
                    ivAvatar.setImageResource(R.drawable.avatar_cero);
                    userAux.setAvatar(0);
                break;
            case R.id.avatar_1x2:
                    ivAvatar.setImageResource(R.drawable.avatar_uno);
                    userAux.setAvatar(1);
                break;
            case R.id.avatar_1x3:
                    ivAvatar.setImageResource(R.drawable.avatar_dos);
                    userAux.setAvatar(2);
                break;
            case R.id.avatar_2x1:
                    ivAvatar.setImageResource(R.drawable.avatar_tres);
                    userAux.setAvatar(3);
                break;
            case R.id.avatar_2x2:
                    ivAvatar.setImageResource(R.drawable.avatar_cuatro);
                    userAux.setAvatar(4);
                break;
            case R.id.avatar_2x3:
                    ivAvatar.setImageResource(R.drawable.avatar_cinco);
                    userAux.setAvatar(5);
                break;
            default:
                    ivAvatar.setImageResource(R.drawable.avatar_cero);
                    userAux.setAvatar(0);
                break;
        }

        ivEditAvatar.setBackgroundColor(Color.GRAY);
        dialog.dismiss();
        Toast.makeText(this, ""+userAux.getAvatar(), Toast.LENGTH_LONG).show();

    }


    private void recargarAd(){

    }

    private void verVideoBonus() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        int dado = (int)(Math.random()*6+90);
        String dadoAux  = ""+dado;
        intent.putExtra("correo", correo);
        intent.putExtra("dado", dadoAux);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
    }

    @Override
    protected void onResume() {
        super.onResume();
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }

    @Override
    protected void onStop() {
        super.onStop();
        // La actividad ya no es visible (ahora está "detenida")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // La actividad está a punto de ser destruida.
    }

}