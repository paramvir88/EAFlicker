package com.eaflicker.paramvir.eaflicker.network;

import com.eaflicker.paramvir.eaflicker.Photo;

import java.util.List;

/**
 * "page":1,
 * "pages":76420,
 * "perpage":10,
 * "total":"764194",
 * "photo":[]
 */
public class PhotosNode {

    int page;
    int pages;
    int perpage;
    String total;
    List<Photo> photo;

}
