
export const getUserId = async () => {
    const response = await fetch('/api/auth/currentUser');
    if (!response.ok) {
      throw new Error('Failed to fetch user ID');
    }
    const data = await response.json();
    return data.id; // Adjust based on your actual response structure
  };
  