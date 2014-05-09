package org.mech.terminator.component;

import java.awt.Color;
import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.ui.Text;
import org.mech.terminator.ui.TextGraphics;

public class Label extends Component {

	private Text text;
	private Color textColor;
	
	private Alignment alignment = Alignment.LEFT;
	private VerticleAlignment valignment = VerticleAlignment.TOP;

	public Label(final String t) {
		this.text = new Text(t);
	}

	public Label() {}

	public Label(final Text text) {
		this.text = text;
	}

	public String getText() {
		return text.toString();
	}

	public void setText(final String text) {
		this.text = new Text(text);
	}

	public void setText(final Text text) {
		this.text = text;
	}

	@Override
	public void onRender(final ITerminal terminal) {
		final TextGraphics tg = new TextGraphics(terminal);
		tg.setFgColor(textColor);

		handleVAlignment(tg);

		tg.drawText(text, handleAlignmentOffset(tg));

	}

	private void handleVAlignment(final TextGraphics tg) {
		int lineOffset = 0;
		switch (valignment) {
		case CENTER:
			final int height = tg.lines(getText());
			if (height < getSize().height) {
				lineOffset = (getSize().height - height) / 2;

				break;
			}
		default:
			lineOffset = 0;
			break;
		}

		tg.newLines(lineOffset);
	}

	private int handleAlignmentOffset(final TextGraphics tg) {
		int offset = 0;

		switch (alignment) {
		case RIGHT:
			offset = handleRight(tg);
			break;
		case CENTER:
			offset = handleCenter(tg);
			break;
		default:
			offset = 0;
		}

		return offset;
	}

	private int handleRight(final TextGraphics tg) {
		final int length = tg.length(getText());
		if (length < getSize().width) {
			return getSize().width - length;
		}

		return 0;
	}

	private int handleCenter(final TextGraphics tg) {
		final int length = tg.length(getText());
		if (length < getSize().width) {
			final int marginSize = getSize().width - length;
			return marginSize / 2;
		}
		return 0;
	}

	public void setTextColor(final Color clr) {
		this.textColor = clr;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(final Alignment alignment) {
		this.alignment = alignment;
	}

	public VerticleAlignment getValignment() {
		return valignment;
	}

	public void setValignment(final VerticleAlignment valignment) {
		this.valignment = valignment;
	}

	public void packHeight(final TextGraphics graphics) {
		final int lines = graphics.lines(getText());
		setPrefferedSize(Dimension.of(-1, lines));
	}

	public void packWidth(final TextGraphics graphics) {
		final int w = graphics.length(getText());
		setPrefferedSize(Dimension.of(w, -1));
	}

	public void pack(final TextGraphics graphics) {
		final int w = graphics.length(getText());
		final int lines = graphics.lines(getText());
		setPrefferedSize(Dimension.of(w, lines));
	}

}
