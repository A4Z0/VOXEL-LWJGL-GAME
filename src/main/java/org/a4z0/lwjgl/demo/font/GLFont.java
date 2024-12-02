package org.a4z0.lwjgl.demo.font;

import org.lwjgl.BufferUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public final class GLFont {

    private final int glTexture;
    private final int Width, Height;

    private final Map<Integer, Glyph> Glyphs = new HashMap<>();

    /**
    * Construct a {@link GLFont}.
    *
    * @param uri File path based on "src/main/resources".
    * @param size Font size.
    */

    GLFont(String uri, float size) throws IOException, FontFormatException {
        this(GLFont.class.getClassLoader().getResourceAsStream(uri), size);
    }

    /**
    * Construct a {@link GLFont}.
    *
    * @param stream {@link InputStream} from a ".tff".
    * @param size Font size.
    */

    GLFont(InputStream stream, float size) throws FontFormatException, IOException {
        this(Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(Font.PLAIN, size));
    }

    /**
    * Construct a {@link GLFont}.
    *
    * @param _Font {@link Font} that will be used to create this {@link GLFont}.
    */

    GLFont(Font _Font) {
        int _ImageWidth = 0;
        int _ImageHeight = 0;

        for(int i = 32; i < 256; i++) {
            if(i == 127)
                continue;

            BufferedImage _CharImage = createCharImage(_Font, i, false);

            if(_CharImage == null)
                continue;

            _ImageWidth += _CharImage.getWidth();
            _ImageHeight = Math.max(_ImageHeight, _CharImage.getHeight());
        }

        this.Width = _ImageWidth;
        this.Height = _ImageHeight;

        BufferedImage _Image = new BufferedImage(_ImageWidth, _ImageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D _G2D = _Image.createGraphics();

        int _X = 0;

        for(int i = 32; i < 256; i++) {
            if (i == 127)
                continue;

            BufferedImage _CharImage = createCharImage(_Font, i, false);

            if (_CharImage == null)
                continue;

            int _CharWidth = _CharImage.getWidth();
            int _CharHeight = _CharImage.getHeight();

            Glyph _Glyph = new Glyph(i, _X, (_Image.getHeight() - _CharHeight), _CharWidth, _CharHeight, 0, 0, 0);
            _G2D.drawImage(_CharImage, _X, 0, null);
            _X += _Glyph.getWidth();

            this.Glyphs.put(i, _Glyph);
        }

        _G2D.dispose();

        this.glTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, this.glTexture);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        int[] _Pixels = new int[_Image.getWidth() * _Image.getHeight()];
        _Image.getRGB(0, 0, _Image.getWidth(), _Image.getHeight(), _Pixels, 0, _Image.getWidth());

        ByteBuffer _Buffer = BufferUtils.createByteBuffer(_Image.getWidth() * _Image.getHeight() * 4);

        for(int y = 0; y < _Image.getHeight(); y++) {
            for(int x = 0; x < _Image.getWidth(); x++) {
                int _Pixel = _Pixels[y * _Image.getWidth() + x];

                _Buffer.put((byte) ((_Pixel >> 16) & 0xFF));
                _Buffer.put((byte) ((_Pixel >> 8) & 0xFF));
                _Buffer.put((byte) (_Pixel & 0xFF));
                _Buffer.put((byte) ((_Pixel >> 24) & 0xFF));
            }
        }
        _Buffer.flip();

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, _Image.getWidth(), _Image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, _Buffer);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static BufferedImage createCharImage(Font _Font, int _Unicode, boolean _AntiAlias) {
        BufferedImage _Image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D _G2D = _Image.createGraphics();

        if(_AntiAlias)
            _G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        _G2D.setFont(_Font);
        FontMetrics Metrics = _G2D.getFontMetrics();
        _G2D.dispose();

        int _CharWidth = Metrics.charWidth((char) _Unicode);
        int _CharHeight = Metrics.getHeight();

        if(_CharWidth == 0)
            return null;

        _Image = new BufferedImage(_CharWidth, _CharHeight, BufferedImage.TYPE_INT_ARGB);
        _G2D = _Image.createGraphics();

        if(_AntiAlias)
            _G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        _G2D.setFont(_Font);
        _G2D.setPaint(Color.WHITE);
        _G2D.drawString(String.valueOf((char) _Unicode), 0, Metrics.getAscent());
        _G2D.dispose();

        return _Image;
    }

    /**
    * @return the ID.
    */

    public int getID() {
        return this.glTexture;
    }

    /**
    * @return the Width.
    */

    public int getWidth() {
        return this.Width;
    }

    /**
    * @return the Height.
    */

    public int getHeight() {
        return this.Height;
    }

    /**
    * Retrieves a {@link Glyph}.
    *
    * @param unicode Unicode.
    *
    * @return a {@link Glyph}.
    */

    public Glyph getGlyph(int unicode) {
        return this.Glyphs.get(unicode);
    }

    /**
    * Construct a {@link GLFont}.
    *
    * @param uri File path based on "src/main/resources".
    * @param size Font size.
    *
    * @return a new {@link GLFont}.
    */

    public static GLFont create(String uri, float size) {
        try {
            return new GLFont(uri, size);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
