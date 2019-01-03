package com.eaflicker.paramvir.eaflicker.network;

public interface GenericApiListener<S,F> {

    void onSucsess(S ressponse);
    void onFailure(F failure);

}
