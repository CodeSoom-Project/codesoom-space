export default function Button({onClick, children}: any) {
  return (
    <button
      type="button"
      onClick={onClick}
    >
      {children}
    </button>
  );
}
