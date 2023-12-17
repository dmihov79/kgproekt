package tu.sofia.graphics;


import tu.sofia.maths.Vector2f;
import tu.sofia.maths.Vector3f;

public class Vertex {
	private Vector3f position;
	private Vector2f textureCoord;
	
	public Vertex(Vector3f position, Vector2f textureCoord) {
		this.position = position;
		this.textureCoord = textureCoord;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector2f getTextureCoord() {
		return textureCoord;
	}
}