package com.xjy.security.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1916:18
 * @Description: 考虑到验证码公用，放到core中，这里是子类图片验证码，要存图片，扩展之前的ValidateCode
 * @Modified By:
 */
public class ImageCode  extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code,int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
