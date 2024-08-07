package org.spider.eurekaclient.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProduct implements Serializable {
    private long id;
    private String title;
    private double price;
    private FakeStoreCategory fakeStoreCategory;
    private String description;
    private String image;
}
