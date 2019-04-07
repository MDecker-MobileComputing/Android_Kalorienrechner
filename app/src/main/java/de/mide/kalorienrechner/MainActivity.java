package de.mide.kalorienrechner;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;


/**
 * MainActivity für Berechnung Gesamtenergie von drei Mahlzeiten an einem Tag.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class MainActivity extends FragmentActivity
                          implements View.OnClickListener {

    /** Fragment für Eingabe Energie des Frühstücks. */
    protected EnergieFragment _energieFruehstueck = null;

    /** Fragment für Eingabe Energie des Mittagessens. */
    protected EnergieFragment _energieMittagessen = null;

    /** Fragment für Eingabe Energie des Abendessens. */
    protected EnergieFragment _energieAbendessen = null;


    /**
     * Lifecycle-Methode, lädt Layout-Datei und füllt die
     * drei Member-Variablen mit Referenzen auf die Fragmente.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button berechnenButton = findViewById(R.id.berechnen_button);
        berechnenButton.setOnClickListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        _energieFruehstueck = (EnergieFragment) fragmentManager.findFragmentById(R.id.energie_fruehstueck);
        _energieMittagessen = (EnergieFragment) fragmentManager.findFragmentById(R.id.energie_mittagessen);
        _energieAbendessen  = (EnergieFragment) fragmentManager.findFragmentById(R.id.energie_abendessen );
    }


    /**
     * Event-Handler für Berechnen-Button.
     *
     * @param view  UI-Element (Button), das das Event ausgelöst hat.
     */
    @Override
    public void onClick(View view) {

        int fruehstueckKilokalorien  = _energieFruehstueck.getKilokalorien();
        if (fruehstueckKilokalorien < 0) {
            zeigeToastNachricht("Bitte Wert für Frühstück eingeben!");
            return;
        }

        int mittagessenKilokalorien = _energieMittagessen.getKilokalorien();
        if (mittagessenKilokalorien < 0) {
            zeigeToastNachricht("Bitte Wert für Mittagessen eingeben!");
            return;
        }

        int abendessenKilokalorien  = _energieAbendessen.getKilokalorien();
        if (abendessenKilokalorien < 0) {
            zeigeToastNachricht("Bitte Wert für Mittagessen eingeben!");
            return;
        }

        int summe = fruehstueckKilokalorien + mittagessenKilokalorien + abendessenKilokalorien;
        zeigeToastNachricht("Gesamtenergie: " + summe + " kCal");

        keyboardEinklappen(view);
    }


    /**
     * Zeigt einen Text in einem Toast an (Nachricht verschwindet also von selbst wieder
     * und blockiert nicht die UI).
     *
     * @param toastNachricht  Nachricht, die anzuzeigen ist.
     */
    protected void zeigeToastNachricht(String toastNachricht) {

        Toast.makeText(this, toastNachricht, Toast.LENGTH_LONG).show();
    }


    /**
     * Virtuelles Keyboard wieder "einklappen".
     * Lösung nach
     * <a href="https://stackoverflow.com/a/17789187/1364368">https://stackoverflow.com/a/17789187/1364368</a>
     *
     * @param view  UI-Element, um Windows-Token zu erhalten.
     */
    public void keyboardEinklappen(View view) {

        InputMethodManager imm = (InputMethodManager)
                getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
